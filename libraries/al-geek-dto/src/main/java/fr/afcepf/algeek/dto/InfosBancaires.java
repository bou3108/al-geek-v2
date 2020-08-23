package fr.afcepf.algeek.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfosBancaires implements Serializable {

    private Long id;
    private String typeCarte;
    private String numCarte;
    private Date dateCarte;
    private String cryptogramme;

}
