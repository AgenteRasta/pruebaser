package com.choucair.tasks;

import com.choucair.interactions.OurGet;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class DoGet implements Task {

    private String resource;

    public DoGet withTheResource(String resource){
        this.resource=resource;
        return this;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OurGet.to(resource)
                        .with(request->request.relaxedHTTPSValidation())
                        .with(request->request.contentType(ContentType.JSON))
        );
    }

    public static DoGet doGet(){
        return new DoGet();
    }
}
