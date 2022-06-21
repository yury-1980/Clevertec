package ru.clevertec.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

public class DownloadCheckImage implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.task ('downloadCheckImage',type: CustomTask){
//            sourceUrl = 'https://github.com/yury-1980/hello-world/raw/main/file.png'
            sourceUrl = 'https://raw.githubusercontent.com/yury-1980/hello-world/main/products.txt'
//            target = new File('src/main/resources/file.png')
            target = new File('src/main/resources/products.txt')
        }
    }
}