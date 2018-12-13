package com.interswitch.microservice_test.Controller;
import com.interswitch.microservice_test.Model.UserModel;
import com.interswitch.microservice_test.UserModelRepository.UserModelRepository;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/registration")
public class UserController {
    private List<UserModel> users;
    private UserModelRepository userModelRepository;
    public  UserController(UserModelRepository userModelRepository){
        this.userModelRepository = userModelRepository;
        users = new ArrayList<>();
        users.add(new UserModel("toluwani","ofobrukweta","https://oghenerukevwejeff@gmail.com","obi@gmail.com",23,"2") );
        users.add(new UserModel("jeff","Dean","https://Deanjeff@gmail.com","obi@gmail.com",23,"3") );
    }
    @RequestMapping(value = "/allUsers",method = RequestMethod.GET)
    public List<UserModel> getAll(){
        return  users;
    }

    @RequestMapping(value = "/afford/{roomNumber}",method = RequestMethod.GET)
    public  List<UserModel> getAffordable(@PathVariable double roomNumber){
        return users.stream().filter(x->x.getRoomNumber() <= roomNumber)
                .collect(Collectors.toList());
    }
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public  List<UserModel> createUser(@RequestBody @Valid  UserModel UserCred, Errors errors){
        //this errors.hasErrors returns boolean
        if(errors.hasErrors()){
            return users;
        }
        users.add(UserCred);
        return users;
    }



    @RequestMapping(value = "/add/{id}/{username}",method = RequestMethod.POST)
    public UserModel addUserToCatch(@PathVariable("id") final String id ,@RequestBody final String url,@PathVariable("username") final String username ){

            userModelRepository.save(new UserModel(username,"Dean",url,"obi@gmail.com",23,id));
            return userModelRepository.findById(id);
    }
    @RequestMapping(value = "/showUsersInCatch",method = RequestMethod.GET)
    public Map<String,UserModel> showUsersInCatch(){
        return userModelRepository.findAll();
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Map<String,UserModel> delete(@PathVariable final String id){
        userModelRepository.delete(id);
        return all();
    }
    @GetMapping("/all")
    public Map<String,UserModel> all(){
        return userModelRepository.findAll();
    }


}
