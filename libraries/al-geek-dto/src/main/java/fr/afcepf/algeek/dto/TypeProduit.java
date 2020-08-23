package fr.afcepf.algeek.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class TypeProduit implements Serializable {

    private Long id;
    private String nom;

}
