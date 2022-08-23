package ru.clevertec.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

class CustomTask extends DefaultTask {

    @Input
    String sourceUrl

    @OutputFile
    File target

    @TaskAction
    void download() {
        try {
            ant.get(src: sourceUrl, dest: target)
        }
        catch (Exception e) {
            println(e)
        }
    }
}