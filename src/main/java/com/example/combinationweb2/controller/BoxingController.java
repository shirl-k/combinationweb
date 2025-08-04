package com.example.combinationweb2.controller;

import com.example.combinationweb2.dto.BoxingForm;
import com.example.combinationweb2.dto.CommentDto;
import com.example.combinationweb2.entity.Boxing;
import com.example.combinationweb2.repository.BoxingRepository;
import com.example.combinationweb2.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class BoxingController {

    @Autowired
    private BoxingRepository boxingRepository; //boxingRepository 객체 선언

    @Autowired
    private CommentService commentService;

    @GetMapping("boxing/new")
    public String creatingForm() {

        return "boxing/new";
    }
    @PostMapping("/boxing/create")
    public String creatingFormData(BoxingForm form) {
        log.info(form.toString());
        //System.out.println(form.toString());

        Boxing boxing = form.toEntity();
        log.info(boxing.toString());
        //System.out.println(boxing.toString());

        Boxing saved = boxingRepository.save(boxing);
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "redirect:/boxing/" + saved.getId();
    }
    @GetMapping("/boxing/{id}") //데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model) {  //매개변수로 id 받아오기
        log.info("id = " + id);
        //1.id를 조회해 데이터 가져오기
        Boxing boxingEntity = boxingRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id); //(댓글 목록 뷰 페이지 반환)
        //2.모델에 데이터 등록하기
        model.addAttribute("boxing",boxingEntity);
        model.addAttribute("commentDtos",commentDtos); //변수값을 "변수명"이라는 이름으로 추가
        //3.뷰 페이지 반환하기
        return "boxing/show";
    }
    @GetMapping("/boxing")
    public String index(Model model) {
        //1.모든 데이터 가져오기
        ArrayList<Boxing> boxingEntityList = boxingRepository.findAll();
        //2.모델에 데이터 등록하기
        model.addAttribute("boxingList", boxingEntityList);
        //3.뷰 페이지 설정하기
        return "boxing/index";
    }
    @GetMapping("/boxing/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {

        Boxing boxingEntity = boxingRepository.findById(id).orElse(null);

        model.addAttribute("boxing", boxingEntity);
        return "boxing/edit";
    }
    @PostMapping("/boxing/update")
    public String update(BoxingForm form) {
        log.info(form.toString());
        Boxing boxingEntity = form.toEntity();
        log.info(boxingEntity.toString());
        Boxing target = boxingRepository.findById(boxingEntity.getId()).orElse(null);
        if(target !=null) {
            boxingRepository.save(boxingEntity);
            return "redirect:/boxing/" + boxingEntity.getId();
        }
        return "";
    }
    @GetMapping("/boxing/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes delmsg) {
        log.info("삭제 요청이 들어왔습니다!");
        //삭제할 대상 가져오기
        Boxing target = boxingRepository.findById(id).orElse(null);
        log.info(target.toString());

        if (target != null) {
            boxingRepository.delete(target);//db에서 삭제 수행
            delmsg.addFlashAttribute("msg", "삭제됐습니다.");
        }
            return "redirect:/boxing";
        }
    }

