package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.domain.Item;
import sec.project.repository.AccountRepository;
import sec.project.repository.ItemRepository;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/items";
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String loadForm(Authentication authentication, Model model) {
        model.addAttribute("items", accountRepository.findByUsername(authentication.getName()).getItems());
        return "form";
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public String submitForm(Authentication authentication, @RequestParam String name, @RequestParam String amount) {
        Account account = accountRepository.findByUsername(authentication.getName());

        itemRepository.save(new Item(name, amount, account));
        return "redirect:/form";
    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        itemRepository.delete(id);
        return "redirect:/form";
    }
}
