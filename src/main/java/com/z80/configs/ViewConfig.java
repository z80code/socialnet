package com.z80.configs;

public class ViewConfig {

    final private String basePath;
    final private String suffix;

    private static ViewConfig instance;

    public static ViewConfig getInstance() {
        if (instance==null){
            instance = new ViewConfig("/WEB-INF/views", ".jsp");
        }
        return instance;
    }

    private ViewConfig(String basePath, String suffix) {
        this.basePath = basePath;
        this.suffix = suffix;
    }

    public String getBasePath() {
        return basePath;
    }

    public String getSuffix() {
        return suffix;
    }

}
