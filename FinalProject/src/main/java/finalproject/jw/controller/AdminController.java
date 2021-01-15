package finalproject.jw.controller;

import finalproject.jw.dto.AddCategoryDTO;
import finalproject.jw.dto.CreateDTO;
import finalproject.jw.exception.NotAdminException;
import finalproject.jw.exception.NotLoggedInException;
import finalproject.jw.service.AdminService;
import finalproject.jw.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/addCategory")
    public ResponseEntity<CreateDTO> addCategory(@RequestBody @Valid AddCategoryDTO category, HttpServletRequest request) throws NotLoggedInException, NotAdminException {
        Validation.validateAdminUser(request);
        Long categoryId = adminService.addCategory(category);
        return new ResponseEntity<CreateDTO>(new CreateDTO(HttpStatus.CREATED.value(), "Category created successfully!", categoryId), HttpStatus.CREATED);
    }

}
