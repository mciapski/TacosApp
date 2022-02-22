package com.Taco.Taco.data;

import com.Taco.Taco.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcIngredientRepository implements IngredientRepository {
  public JdbcTemplate jdbc;

  @Autowired
  public JdbcIngredientRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }
//Mapowanie każdego wiersza na obiekt
  private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
    return new Ingredient(rs.getString("id"),
        rs.getString("name"),
        Ingredient.Type.valueOf(rs.getString("type")));
  }

  @Override
  public Iterable<Ingredient> findAll() {
    return jdbc.query("select id, name, type from Ingredient",
        this::mapRowToIngredient);
  }
  //Alternatywa, czyli bez użycia lambdy i referencji:
  //
//  @Override
//  public Iterable<Ingredient> findAll() {
//    return jdbc.query("select id, name, type from Ingredient",
//        new RowMapper<Ingredient>(){
//        public Ingredient mapRow(ResultSet rs, int rowNum)throws SQLException{
//        return new Ingredient(
//        rs.getString("id"),
//        rs.getString("name"),
//        Ingredient.Type.valueOf(rs.getString("type"))
//        }
//        });
//  }

  @Override
  public Ingredient findById(String id) {
    return jdbc.queryForObject("select id, name, type from Ingredient where id=?",
        this::mapRowToIngredient, id);
  }

  @Override
  public Ingredient save(Ingredient ingredient) {
    jdbc.update("insert into Ingredient (id, name, values(?,?,?)",
        ingredient.getId(),
        ingredient.getName(),
        ingredient.getType().toString());
    return ingredient;
  }
}
