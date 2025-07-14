package pe.edu.vallegrande.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class thingsEntity {

    private int id;
    private String name;
    private String material;
    private String is_required;
    private String category;
    private String proveedor;
    private int stock;
    private String description;
    private Date creation_date;
    private Date last_update;
    private boolean active;


}
