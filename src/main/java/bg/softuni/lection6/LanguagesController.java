package bg.softuni.lection6;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LanguagesController {

    private final String defaultLang = "bg";
    private final List<String> allLangs = List.of("en", "bg", "de");

    @PostMapping("/save")
    public String save(@RequestParam String lang,
                       HttpServletResponse response,
                       HttpSession session) {
        session.setAttribute("lang", lang);
//        Cookie cookie = new Cookie("langCookie", lang);
//        response.addCookie(cookie);

        return "redirect:/all";

    }

    @GetMapping("/all")
    public String allLangs(Model model
            , HttpSession session){
        Object preferredLang = session.getAttribute("lang");
        if (preferredLang == null) {
            preferredLang = defaultLang;
        }
            //,@CookieValue(value = "langCookie", required = false, defaultValue = defaultLang) String lang) {
        model.addAttribute("all", allLangs);
        //model.addAttribute("preferredLang", lang);
        model.addAttribute("preferredLang", preferredLang);
        return "languages";
    }
}
