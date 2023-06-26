package com.choucair.tasks;

import com.choucair.interactions.OurDelete;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class DoDelete implements Task {

    private String resource;

    public DoDelete withTheResource(String resource){
        this.resource=resource;
        return this;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OurDelete.to(resource)
                        .with(request->request.relaxedHTTPSValidation())
                        .with(request->request.contentType(ContentType.JSON))
        );
    }

    public static DoDelete doDelete(){
        return new DoDelete();
    }
}
