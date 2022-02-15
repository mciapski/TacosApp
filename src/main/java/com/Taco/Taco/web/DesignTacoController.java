package com.Taco.Taco.web;

import com.Taco.Taco.Ingredient;
import com.Taco.Taco.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/design")
public class DesignTacoController {

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type){
    return ingredients.stream()
        .filter(x->x.getType().equals(type)).collect(Collectors.toList());
  }


  @GetMapping
  public String showDesignForm(Model model){
    List<Ingredient> ingredients = List.of(
        new Ingredient("FLTO", "pszenna", Ingredient.Type.WRAP),
        new Ingredient("COTO", "kukurydziana", Ingredient.Type.WRAP),
        new Ingredient("GRBF", "mielona wołowina", Ingredient.Type.PROTEIN),
        new Ingredient("CARN", "kawałki mięsa", Ingredient.Type.PROTEIN),
        new Ingredient("TMTO", "pomidory pokrojone w kostke", Ingredient.Type.VEGGIES),
        new Ingredient("LETC", "sałata", Ingredient.Type.VEGGIES),
        new Ingredient("CHED", "cheddar", Ingredient.Type.CHEESY),
        new Ingredient("JACK", "Monterey Jack", Ingredient.Type.CHEESY),
        new Ingredient("SLSA", "pikantny sos pomidorowy", Ingredient.Type.SAUCE),
        new Ingredient("SRCR", "śmietana", Ingredient.Type.SAUCE)
    );
    Ingredient.Type[] types = Ingredient.Type.values();
    for (Ingredient.Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients,type));
    }
//    Arrays.stream(types)
//        .forEach(a -> model.addAttribute(
//            a.toString().toLowerCase(),
//            ingredients.stream().filter(i -> a.equals(i.getType())).collect(Collectors.toList())));
    model.addAttribute("design" , new Taco());
    return "design";
  }

  @PostMapping
  public String processDesign(@ModelAttribute("design") Taco design, Model model){
    //zapisywanie projektu przygotowanego taco
    log.info("Przetwarzanie projektu taco:" + design);
    return "redirect:/orders/current";
  }

}
