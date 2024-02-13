package WebService.Controller;

import WebService.Models.User;
import WebService.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class APIController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome to this RESTful WebService. /n" +
                "Here you can add users to the database and show a list of them all. /n" +
                "New Features will be added soon, so keep track of the developments.";
    }

    @GetMapping(value = "/users")
    public List<User> getUsers () {return userRepo.findAll();}

    @PostMapping(value = "/save")
    public String saveUser (@RequestBody User user) {
        userRepo.save(user);
        return "Successfully saved.";
    }

    @PutMapping(value = "update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        User updatedUser = userRepo.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        userRepo.save(updatedUser);
        return "Update successful.";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        User UserForDeletion = userRepo.findById(id).get();
        userRepo.delete(UserForDeletion);
        return "User with ID " + id + " successfully deleted.";
    }
}
