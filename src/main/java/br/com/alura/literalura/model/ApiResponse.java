package br.com.alura.literalura.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public record ApiResponse(
    @Expose List<BookRecord> results
) {
}
