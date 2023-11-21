package com.example.mobilele.web;

import com.example.mobilele.model.dto.CreateOfferDTO;
import com.example.mobilele.model.dto.OfferDetailDTO;
import com.example.mobilele.model.enums.EnginEnum;
import com.example.mobilele.service.BrandService;
import com.example.mobilele.service.OfferService;
import com.example.mobilele.service.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/offer")
public class OfferController {

    private final OfferService offerService;
    private final BrandService brandService;

    public OfferController(OfferService offerService, BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }


    @ModelAttribute("engines")
    public EnginEnum[] engines() {
        return EnginEnum.values();
    }

    @GetMapping("/add")
    public String add(Model model) {

        if(!model.containsAttribute("createOfferDTO")) {
            model.addAttribute("createOfferDTO", CreateOfferDTO.empty());
        }

        model.addAttribute("brands", brandService.getAllBrands());
        return "offer-add";
    }

    @PostMapping("/add")
    public String add(@Valid CreateOfferDTO createOfferDTO,
                      BindingResult bindingResult,
                      RedirectAttributes rAtt,
                      @AuthenticationPrincipal UserDetails seller) {

        if(bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("createOfferDTO", createOfferDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.createOfferDTO", bindingResult);
            return "redirect:/offer/add";
        }

        UUID newOfferUUID = offerService.createOffer(createOfferDTO, seller);

        return "redirect:/offer/" + newOfferUUID;
    }

    @GetMapping("/{uuid}")
    public String details(@PathVariable("uuid") UUID uuid, Model model) {

       OfferDetailDTO offerDetailDTO =  offerService
                .getOfferDetail(uuid)
                .orElseThrow(() -> new ObjectNotFoundException("" +
                        "Offer with uuid " + uuid + "was not found!"));
       model.addAttribute("offer", offerDetailDTO);

        return "details";
    }

    @DeleteMapping("/{uuid}")
    public String delete(@PathVariable UUID uuid, Model model) {

        offerService.deleteOffer(uuid);
        return "redirect:/offers/all";
    }
}
