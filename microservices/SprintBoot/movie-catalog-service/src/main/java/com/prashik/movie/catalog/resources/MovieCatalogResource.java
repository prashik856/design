package com.prashik.movie.catalog.resources;

import com.prashik.movie.catalog.models.CatalogItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Just with this one annotation, now this class is a rest controller
// We also want to tell sprintboot that if /api/v1/catalog api is called, it needs to bootup this API
@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    // We want Sprintboot to treat this method as an API
    // it should be available at /api/v1/catalog/[userId]
    // The curly braces around userID tells springboot that it is a parameter.
    // the PathVariable Annotation tells getCatalog method to pass the userID variable
    // api request to method.
    @RequestMapping("/{userId}")
    List<CatalogItem> getCalalog(@PathVariable("userId") String userId) {
        List<CatalogItem> catalogItemList = new ArrayList<>();
        catalogItemList.add(new CatalogItem("Transformers", "Move about robots and stuff", 3));
        catalogItemList.add(new CatalogItem("Hangover", "Move about 4 frinds bucking around", 4));
        catalogItemList.add(new CatalogItem("Harry Potter", "Magic boy", 4));
        catalogItemList.add(new CatalogItem("Bad Boys", "Cop Story", 3));
        catalogItemList.add(new CatalogItem("Deadpool", "Superhero Movie", 5));
        catalogItemList.add(new CatalogItem("Spider-man", "Friendly neighbourhood Spidy", 6));
        return catalogItemList;
    }
}
