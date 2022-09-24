package com.rumahsakit;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "Rumah Sakit APIs",
                description = "Application",
                version = "1.0.0",
                license = @License(
                        name = "MIT",
                        url = "http://localhost:8080"
                )
        ),
        tags = @Tag(
            name = "Rumah Sakit API's",
            description = "Documentaion"
        )
)
public class RumahSakitApplication extends Application {
}
