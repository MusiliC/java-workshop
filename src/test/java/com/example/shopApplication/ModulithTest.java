package com.example.shopApplication;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class ModulithTest {

    ApplicationModules modules = ApplicationModules.of(ShopApplication.class);

    @Test
    void shouldVerifyModularity() {
        modules.verify();
    }

    @Test
    void shouldGenerateModuleDocumentation() {
        new Documenter(modules)
                .writeDocumentation()
                .writeIndividualModulesAsPlantUml();
    }
}
