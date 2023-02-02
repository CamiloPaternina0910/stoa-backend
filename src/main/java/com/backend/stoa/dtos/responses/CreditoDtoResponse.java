package com.backend.stoa.dtos.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditoDtoResponse {

    private Long id;

    private List<FacturaDtoResponse> facturas;

}
