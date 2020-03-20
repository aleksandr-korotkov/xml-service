package services;

import entities.RootElement;

import java.util.Optional;

public interface JsonService {

    void convertObjectToJSon(RootElement element);

    Optional<RootElement> convertJsonToObject();
}
