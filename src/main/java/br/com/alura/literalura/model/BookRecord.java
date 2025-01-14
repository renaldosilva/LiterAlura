package br.com.alura.literalura.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public record BookRecord(
       @Expose String title,
       @Expose List<AuthorRecord> authors,
       @Expose List<String> languages,
       @Expose Long downloadCount
) {
}
