package com.Taco.Taco;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class Ingredient {
  private final String id;
  private final String name;
  private final Type type;

  public static enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESY, SAUCE;

  }
}