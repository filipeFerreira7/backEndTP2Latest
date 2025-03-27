package br.unitins.tp2.dto;

import java.util.List;

public record PaginacaoResponseDTO<T>(
        long total,
        int page,
        int pageSize,
        List<T> results
) {
    public static <T>PaginacaoResponseDTO<T> valueOf(long total, int page, int pageSize, List<T> results) {
        return new PaginacaoResponseDTO<>(total,page,pageSize,results);
    }
}
