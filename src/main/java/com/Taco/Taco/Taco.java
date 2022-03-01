package com.Taco.Taco;

import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.convert.DataSizeUnit;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {
  private Long id;
  private Date createdAt;

  @NotNull
  @Size(min=5, message="Nazwa musi składać się z przynajmniej pięciu znaków")
  private String name;
  @Size(min=1, message = "Musi zawierać przynajmniej jeden składnik")
  private List<Ingredient> ingredients;
}
