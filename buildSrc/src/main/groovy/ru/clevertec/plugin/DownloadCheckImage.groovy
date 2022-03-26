package ru.clevertec.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class DownloadCheckImage implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.task ('downloadCheckImage',type: CustomTask){
            sourceUrl = 'https://github.com/yury-1980/hello-world/raw/main/file.png'
            target = new File('src/main/resources/file.png')
        }
    }
}
