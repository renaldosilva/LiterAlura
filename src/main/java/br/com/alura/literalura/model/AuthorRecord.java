package br.com.alura.literalura.model;


import com.google.gson.annotations.Expose;


public record AuthorRecord(
        @Expose String name,
        @Expose Integer birthYear,
        @Expose Integer deathYear
) {
}
