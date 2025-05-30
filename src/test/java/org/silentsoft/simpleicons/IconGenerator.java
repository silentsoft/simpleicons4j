package org.silentsoft.simpleicons;

import net.lingala.zip4j.ZipFile;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.silentsoft.arguments.parser.Arguments;
import org.silentsoft.nullify.Nullify;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class IconGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(IconGenerator.class);

    public static void main(String[] args) throws Exception {
        Arguments arguments = Arguments.parser(args).parse();

        String simpleIconsVersion = arguments.getValue("--simple-icons-version");
        downloadSimpleIcons(simpleIconsVersion);

        TreeMap<String, String> iconMap = new TreeMap<>();
        generateIconClasses(iconMap);
        generateSlugsMarkdownFile(iconMap);
        LOGGER.info(() -> "Generated " + iconMap.size() + " icons.");
    }

    private static void downloadSimpleIcons(String simpleIconsVersion) throws Exception {
        String downloadUrl;
        if (Nullify.isNull(simpleIconsVersion)) {
            downloadUrl = "https://github.com/simple-icons/simple-icons/archive/refs/heads/master.zip";
            LOGGER.info(() -> "No 'simple-icons-version' specified. Downloading latest master branch...");
        } else {
            downloadUrl = String.format("https://github.com/simple-icons/simple-icons/archive/refs/tags/%s.zip", simpleIconsVersion);
            LOGGER.info(() -> String.format("Downloading simple-icons %s...", simpleIconsVersion));
        }

        Path zipPath = Paths.get(System.getProperty("user.dir"), "target/simple-icons.zip");
        Path extractDir = Paths.get(System.getProperty("user.dir"), "target/simple-icons");

        Files.deleteIfExists(zipPath);
        if (Files.exists(extractDir)) {
            Files.walk(extractDir).sorted(Comparator.reverseOrder()).forEach(path -> {
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        Files.createDirectories(extractDir);

        try (InputStream inputStream = new URL(downloadUrl).openStream()) {
            Files.copy(inputStream, zipPath, StandardCopyOption.REPLACE_EXISTING);
        }

        String zipRoot = Nullify.isNull(simpleIconsVersion) ? "simple-icons-master/" : String.format("simple-icons-%s/", simpleIconsVersion);
        try (ZipFile zipFile = new ZipFile(zipPath.toString())) {
            zipFile.extractFile(zipRoot.concat("data/"), System.getProperty("user.dir"), "/target/simple-icons/data");
            zipFile.extractFile(zipRoot.concat("icons/"), System.getProperty("user.dir"), "/target/simple-icons/icons");
        }
    }

    private static void generateIconClasses(Map<String, String> iconMap) throws Exception {
        getSimpleIcons().forEach(json -> {
            try {
                Icon icon = toIcon((JSONObject) json);
                fill(icon);

                String className = Utils.slugToClassName(icon.getSlug());
                String template = read(IconGenerator.class.getResourceAsStream("/IconTemplate.java"));
                template = template.replaceAll("\\$\\{className\\}", className);

                template = template.replaceAll("\\$\\{title\\}", icon.getTitle());
                template = template.replaceAll("\\$\\{slug\\}", icon.getSlug());

                StringBuilder contentBuilder = new StringBuilder();
                if (Nullify.isNotNull(icon.getTitle())) {
                    contentBuilder.append("\n");
                    contentBuilder.append(String.format("        setTitle(%s);", toStringCode(icon.getTitle())));
                }
                if (Nullify.isNotNull(icon.getSlug())) {
                    contentBuilder.append("\n");
                    contentBuilder.append(String.format("        setSlug(%s);", toStringCode(icon.getSlug())));
                }
                if (Nullify.isNotNull(icon.getHex())) {
                    contentBuilder.append("\n");
                    contentBuilder.append(String.format("        setHex(%s);", toStringCode(icon.getHex())));
                }
                if (Nullify.isNotNull(icon.getSource())) {
                    contentBuilder.append("\n");
                    contentBuilder.append(String.format("        setSource(%s);", toStringCode(icon.getSource())));
                }
                if (Nullify.isNotNull(icon.getSvg())) {
                    contentBuilder.append("\n");
                    contentBuilder.append(String.format("        setSvg(%s);", toStringCode(icon.getSvg())));
                }
                if (Nullify.isNotNull(icon.getPath())) {
                    contentBuilder.append("\n");
                    contentBuilder.append(String.format("        setPath(%s);", toStringCode(icon.getPath())));
                }
                if (Nullify.isNotNull(icon.getGuidelines())) {
                    contentBuilder.append("\n");
                    contentBuilder.append(String.format("        setGuidelines(%s);", toStringCode(icon.getGuidelines())));
                }
                if (Nullify.isNotNull(icon.getLicense())) {
                    contentBuilder.append("\n");
                    contentBuilder.append("        org.silentsoft.simpleicons.License license = new org.silentsoft.simpleicons.License();");
                    if (Nullify.isNotNull(icon.getLicense().getType())) {
                        contentBuilder.append("\n");
                        contentBuilder.append(String.format("        license.setType(%s);", toStringCode(icon.getLicense().getType())));
                    }
                    if (Nullify.isNotNull(icon.getLicense().getUrl())) {
                        contentBuilder.append("\n");
                        contentBuilder.append(String.format("        license.setUrl(%s);", toStringCode(icon.getLicense().getUrl())));
                    }
                    contentBuilder.append("\n");
                    contentBuilder.append("        setLicense(license);");
                }
                template = template.replace("${content}", contentBuilder.toString());

                write(Paths.get(System.getProperty("user.dir"), "target/generated-sources/org/silentsoft/simpleicons/icons", className + ".java"), template);
                write(Paths.get(System.getProperty("user.dir"), "target/apidocs/org/silentsoft/simpleicons/icons/doc-files", icon.getSlug() + ".svg"), icon.getSvg());

                iconMap.put(icon.getTitle(), icon.getSlug());
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });
    }

    public static JSONArray getSimpleIcons() throws IOException {
        return new JSONArray(read(Files.newInputStream(Paths.get(System.getProperty("user.dir"), "target/simple-icons/data/simple-icons.json"))));
    }

    private static void generateSlugsMarkdownFile(Map<String, String> iconMap) throws Exception {
        StringBuilder slugsBuilder = new StringBuilder();
        slugsBuilder.append("<!-- This file is automatically generated. -->\n");
        slugsBuilder.append("\n");
        slugsBuilder.append("# Simple Icons slugs\n");
        slugsBuilder.append("\n");
        slugsBuilder.append("| Brand name | Brand slug | Class name |\n");
        slugsBuilder.append("| :--- | :--- | :--- |\n");
        iconMap.forEach((title, slug) -> {
            slugsBuilder.append(String.format("| `%s` | `%s` | `%s` |\n", title, slug, Utils.slugToClassName(slug)));
        });
        write(Paths.get(System.getProperty("user.dir"), "slugs.md"), slugsBuilder.toString());
    }

    private static String read(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line.concat("\n"));
        }
        reader.close();
        return builder.toString();
    }

    private static void write(Path path, String content) throws Exception {
        Files.createDirectories(path.getParent());
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(path), StandardCharsets.UTF_8));
        writer.write(content);
        writer.close();
    }

    public static Icon toIcon(JSONObject json) {
        Icon icon = new Icon();
        if (json.has("title")) {
            icon.setTitle(escape(json.getString("title")));
        }
        if (json.has("slug")) {
            icon.setSlug(escape(json.getString("slug")));
        }
        if (json.has("path")) {
            icon.setPath(escape(json.getString("path")));
        }
        if (json.has("source")) {
            icon.setSource(escape(json.getString("source")));
        }
        if (json.has("hex")) {
            icon.setHex(escape(json.getString("hex")));
        }
        if (json.has("guidelines")) {
            icon.setGuidelines(escape(json.getString("guidelines")));
        }
        if (json.has("license")) {
            icon.setLicense(toLicense(json.getJSONObject("license")));
        }
        return icon;
    }

    public static void fill(Icon icon) throws IOException {
        String filename = getIconSlug(icon);

        icon.setSlug(filename);
        try (InputStream inputStream = Files.newInputStream(Paths.get(System.getProperty("user.dir"), "target/simple-icons/icons/", filename.concat(".svg")))) {
            icon.setSvg(read(inputStream).replaceAll("\\r?\\n", ""));
        }
        icon.setPath(svgToPath(icon.getSvg()));
    }

    private static String getIconSlug(Icon icon) {
        return icon.getSlug() != null ? icon.getSlug() : Utils.titleToSlug(icon.getTitle());
    }

    private static String svgToPath(String svg) {
        Matcher matcher = Pattern.compile("<path\\s+d=\"([^\"]*)").matcher(svg);
        return matcher.find() ? matcher.group(1) : "";
    }

    private static String escape(String string) {
        return string == null ? null : string.replaceAll("\"", "\\\\\"");
    }

    private static License toLicense(JSONObject json) {
        if (json == null) {
            return null;
        }

        License license = new License();
        license.setType(json.getString("type"));
        license.setUrl(json.has("url") ? json.getString("url") : "https://spdx.org/licenses/".concat(json.getString("type")));
        return license;
    }

    private static String toStringCode(String value) {
        if (Nullify.isNull(value)) {
            return "null";
        }

        StringBuilder stringBuilder = new StringBuilder();
        String escaped = escape(value);
        // 65534: https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7.3
        if (escaped.length() <= 65534) {
            stringBuilder.append("\"").append(escaped).append("\"");
        } else {
            stringBuilder.append("String.join(\"\", \"").append(String.join("\",\"", Arrays.stream(value.split("(?<=\\G.{65534})")).map(IconGenerator::escape).collect(Collectors.toList()))).append("\")");
        }
        return stringBuilder.toString();
    }

}
