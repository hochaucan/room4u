/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.room4u.dao.FaqFacade;
import com.room4u.dao.FaqFacadeLocal;
import com.room4u.model.Faq;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author JixthSune
 */
@ManagedBean(name = "faq")
@SessionScoped
public class FAQController {

    @EJB
    private FaqFacadeLocal faqFacade;
    private Faq curQ;

    private String faqId;
    private String faqIdDel;

    public String getFaqId() {
        return faqId;
    }

    public void setFaqId(String faqId) {
        this.faqId = faqId;
    }
    private String question;
    private String answer;

    /**
     * Creates a new instance of FAQController
     */
    public FAQController() {
    }

    public List<Faq> getQuestions() {
        return faqFacade.findAll();
    }

    public String addFAQ() {
        curQ = new Faq();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        curQ.setId(1);
        curQ.setQuestion(question.trim());
        curQ.setAnswer(answer.trim());
        curQ.setDate(date);
        faqFacade.create(curQ);
        return "faqAdmin";
    }

    public String updateFAQ()  {
        try {
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Faq faq = faqFacade.find(Integer.parseInt(faqId));
            faq.setQuestion(question.trim());
            faq.setAnswer(answer.trim());
            faq.setDate(date);
            faqFacade.edit(faq);
        } catch (Exception ex) {
            printStackTrace();
        }
        return "faqAdmin";
    }

    public String deleteFAQ() {
        try{
            Faq faq = faqFacade.find(Integer.parseInt(faqIdDel));
            faqFacade.remove(faq);
        }catch(Exception ex)
        {
            printStackTrace();
        }
        return "faqAdmin";
    }

    /**
     * @return the curQ
     */
    public Faq getCurQ() {
        return curQ;
    }

    /**
     * @param curq the curQ to set
     */
    public void setCurQ(Faq curQ) {
        this.curQ = curQ;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * @return the faqIdDel
     */
    public String getFaqIdDel() {
        return faqIdDel;
    }

    /**
     * @param faqIdDel the faqIdDel to set
     */
    public void setFaqIdDel(String faqIdDel) {
        this.faqIdDel = faqIdDel;
    }

}
