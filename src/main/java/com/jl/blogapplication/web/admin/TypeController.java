package com.jl.blogapplication.web.admin;

import com.jl.blogapplication.po.Type;
import com.jl.blogapplication.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;


    @GetMapping("/types")
    public String types(@PageableDefault(size=6, sort = {"id"}, direction = Sort.Direction.DESC)
                            Pageable pageable, Model model){
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }



    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes redirectAttributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 != null){
            result.rejectValue("name", "nameError", "Can not have a duplicate");
        }

        if(result.hasErrors()){
            return "admin/types-input";
        }

        Type t = typeService.saveType(type);

        if(t == null){
            redirectAttributes.addFlashAttribute("message", "Add New Type Failed");
        }else{
            redirectAttributes.addFlashAttribute("message", "Added New Type");
        }
        return "redirect:/admin/types";
    }


    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result, @PathVariable Long id, RedirectAttributes redirectAttributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 != null){
            result.rejectValue("name", "nameError", "Can not have a duplicate");
        }

        if(result.hasErrors()){
            return "admin/types-input";
        }

        Type t = typeService.updateType(id, type);

        if(t == null){
            redirectAttributes.addFlashAttribute("message", "Update Failed");
        }else{
            redirectAttributes.addFlashAttribute("message", "Update Succeed");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        typeService.deleteType(id);
        redirectAttributes.addFlashAttribute("message", "Delete Succeed");
        return "redirect:/admin/types";
    }

}
