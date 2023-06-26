package com.choucair.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.abilities.CallAnApi.as;

public class OurDelete extends RestInteraction {

    private final String resource;

    public OurDelete(String resource) {
        this.resource = resource;
    }
    @Step("{0} executes a DELETE on the resource #resource")
    @Override
    public <T extends Actor> void performAs(T actor) {
        rest().log().all().delete(as(actor).resolve(resource)).then().log().all();
    }

    public static OurDelete to(String resource){
        return instrumented(OurDelete.class,resource);
    }
}
