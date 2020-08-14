package ru.relex.delivery.db.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {
        "dishImage"
})
public class DishTypesModel {
    private String dishType;
    private String dishImage;
}
