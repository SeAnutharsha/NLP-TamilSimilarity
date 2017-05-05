package evaluex;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Answers {

    private String modelAnswer;
    private String studentAnswer;

    public String getModelAnswer() {
        return modelAnswer;
    }

    @XmlElement
    public void setModelAnswer(String modelAnswer) {
        this.modelAnswer = modelAnswer;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    @XmlElement
    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

}
