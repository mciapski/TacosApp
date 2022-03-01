package com.Taco.Taco.web;

import com.Taco.Taco.Ingredient;
import com.Taco.Taco.Order;
import com.Taco.Taco.Taco;
import com.Taco.Taco.data.IngredientRepository;
import com.Taco.Taco.data.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
  private final IngredientRepository ingredientRepository;
  private TacoRepository tacoRepository;

  @Autowired
  public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository){
    this.ingredientRepository=ingredientRepository;
    this.tacoRepository=tacoRepository;
  }
  @ModelAttribute(name="order")
  public Order order(){
    return new Order();
  }
  @ModelAttribute(name="taco")
  public Taco taco(){
    return new Taco();
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type){
    return ingredients.stream()
        .filter(x->x.getType().equals(type)).collect(Collectors.toList());
  }


  @GetMapping
  public String showDesignForm(Model model){
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepository.findAll().forEach(i->ingredients.add(i));
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
  public String processDesign(@Valid @ModelAttribute("design") Taco design, @ModelAttribute Order order, Model model, Errors errors){
    if(errors.hasErrors()){
      return "design";
    }
    //zapisywanie projektu przygotowanego taco
    Taco saved = tacoRepository.save(design);
    order.addDesign(saved);
    return "redirect:/orders/current";
  }

}
