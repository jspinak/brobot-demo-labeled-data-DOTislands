package com.example.demo;

import io.github.jspinak.brobot.actions.BrobotSettings;
import io.github.jspinak.brobot.manageStates.InitialStates;
import org.sikuli.script.ImagePath;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import static com.example.demo.home.Home.Name.HOME;
import static com.example.demo.world.World.Name.WORLD;

@SpringBootApplication
public class GetLabeledDataApp {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(GetLabeledDataApp.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);

        // setup brobot
        ImagePath.setBundlePath("images.sikuli");
        BrobotSettings.mock = true;

        // find initial active States
        InitialStates initialStates = context.getBean(InitialStates.class);
        initialStates.addStateSet(90, WORLD);
        initialStates.addStateSet(10, HOME);
        initialStates.findIntialStates();

        // get and save labeled images
        SaveLabeledImages saveLabeledImages = context.getBean(SaveLabeledImages.class);
        saveLabeledImages.saveImages(100);

    }

}
