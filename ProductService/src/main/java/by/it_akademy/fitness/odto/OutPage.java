package by.it_akademy.fitness.odto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OutPage<Entity> {

    private int number;
    private int size;
    private int totalPages;
    private int totalElements;
    private boolean first;
    private int numberOfElements;
    private boolean last;
    private List<Entity> content;
//    public OutPage() {
//    }
//
//    public int getNumber() {
//        return number;
//    }
//
//    public void setNumber(int number) {
//        this.number = number;
//    }
//
//    public int getSize() {
//        return size;
//    }
//
//    public void setSize(int size) {
//        this.size = size;
//    }
//
//    public int getTotalPages() {
//        return totalPages;
//    }
//
//    public void setTotalPages(int totalPages) {
//        this.totalPages = totalPages;
//    }
//
//    public int getTotalElements() {
//        return totalElements;
//    }
//
//    public void setTotalElements(int totalElements) {
//        this.totalElements = totalElements;
//    }
//
//    public boolean isFirst() {
//        return first;
//    }
//
//    public void setFirst(boolean first) {
//        this.first = first;
//    }
//
//    public int getNumberOfElements() {
//        return numberOfElements;
//    }
//
//    public void setNumberOfElements(int numberOfElements) {
//        this.numberOfElements = numberOfElements;
//    }
//
//    public boolean isLast() {
//        return last;
//    }
//
//    public void setLast(boolean last) {
//        this.last = last;
//    }
//
//    public List<Entity> getContent() {
//        return content;
//    }
//
//    public void setContent(List<Entity> content) {
//        this.content = content;
//    }
}
