package com.utn.estore.controllers;

import com.utn.estore.models.Carritos;
import com.utn.estore.repositories.CarritosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/Carritos") // This means URL's start with /demo (after Application path)
public class CarritosController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private CarritosRepository carritosRepository;

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public @ResponseBody String addNewProduct(
            @RequestParam Integer productoid,
            @RequestParam Integer cantidad,
            @RequestParam Integer usuarioid) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

	carritos n = new Carritos();
	n.setProductoid(productoid);
	n.setCantidad(cantidad);
	n.setUsuarioid(usuarioid);
	carritosRepository.save(n);
        //return "Saved";
    }

    @GetMapping("/delete/{id}") // Map ONLY POST Requests
    public @ResponseBody String deleteUserById(@PathVariable Integer id) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        carritosRepository.deleteById(id);
        return "Deleted";
    }


    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Carritos> getAllUsers() {
        // This returns a JSON or XML with the users
        return carritosRepository.findAll();
    }
}