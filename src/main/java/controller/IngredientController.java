package controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.OpenAIService;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final OpenAIService service;

        // TODO: This should return "vegan", "vegetarian" or "regular" depending on the ingredient.
    @PostMapping
    public String categorizeIngredient(@RequestBody String ingredient) {

        return service.categorizeIngredient(ingredient);
    }

}
