package br.com.alura.literalura.service;

public interface IJsonDeserializer {
    <T> T fromJson(String json, Class<T> clazz);
}
