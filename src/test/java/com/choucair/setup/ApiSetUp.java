package com.choucair.setup;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.log4j.PropertyConfigurator;

import static com.choucair.utils.Log4jValues.LOG4J_PROPERTY_PATH;

public class ApiSetUp extends UIInteractionSteps {
    protected Actor actor = new Actor("Estiven");

    protected void setUp(String urlBase){
        actorCallAnApi(urlBase);
    }

    private void actorCallAnApi(String urlBase){
        actor.can(CallAnApi.at(urlBase));
    }

    private void setUpLog4j(){
        PropertyConfigurator.configure(LOG4J_PROPERTY_PATH.getValue());
    }

    public void waitForMilliseconds(int milliseconds) {
        waitABit(milliseconds);
    }
}
