package com.Taco.Taco;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {
  private Long id;
  private Date placedAt;

  @NotBlank(message="Podanie imienia i nazwiska jest obowiązkowe")
  private String name;
  @NotBlank(message="Podanie ulicy")
  private String street;
  @NotBlank(message="Podanie miasta")
  private String city;
  @NotBlank(message="Podanie województwa")
  private String state;
  @NotBlank(message="Podanie kodu pocztowego jest obowiązkowe")
  private String zip;

@CreditCardNumber(message = "To nie jest prawidłowy numer karty kredytowej")
  private String ccNumber;
@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
    message =" Musi być zachowany format MM/RR" )
  private String ccExpiration;
@Digits(integer = 3, fraction = 0, message = "Nieprawidłowy kod CVV")
  private String ccCVV;

  private List<Taco> tacos = new ArrayList<>();

  public void addDesign(Taco design) {
    this.tacos.add(design);
  }
}
