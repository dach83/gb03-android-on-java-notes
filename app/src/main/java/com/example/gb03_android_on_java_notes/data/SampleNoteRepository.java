package com.example.gb03_android_on_java_notes.data;

import com.example.gb03_android_on_java_notes.domain.NoteEntity;
import com.example.gb03_android_on_java_notes.domain.NoteRepository;

import java.util.ArrayList;
import java.util.List;

public class SampleNoteRepository implements NoteRepository {

    private final List<NoteEntity> notes = new ArrayList<>(sampleNoteList());

    @Override
    public List<NoteEntity> getNotes() {
        return notes;
    }

    @Override
    public void removeNote(NoteEntity note) {
        notes.remove(note);
    }

    private static List<NoteEntity> sampleNoteList() {
        int id = 1;
        List<NoteEntity> list = new ArrayList<>();
        list.add(new NoteEntity(id++, "Кредит", "8700 руб"));
        list.add(new NoteEntity(id++, "Рецепт салата с авокадо", "Нарежьте помидоры, огурцы и " +
                "авокадо крупными кусочками, а лук — тонкими полосками. Добавьте рубленую петрушку, масло, " +
                "лимонный сок, соль и перец и перемешайте."));
        list.add(new NoteEntity(id++, "Ссылки", "1. http://www.specialdefects.com/v2/ - походить по песку\n" +
                "2. https://multator.ru/draw/ - рисовать мультики\n" +
                "3. http://mailfuture.ru/write/ - письмо в будущее\n" +
                "4. http://kakoysegodnyadennedeli.ru/ - какой сегодня день недели\n" +
                "5. http://first-ever.ru/ - сайт про всё самое первое\n" +
                "6. http://proteys.info/404/ - джага\n" +
                "7. http://e.ggtimer.com/ - таймер для ежедневных нужд\n" +
                "8. http://tonematrix.audiotool.com/ - сочинять музыку\n" +
                "9. https://virtualpiano.net/ - играть на синтезаторе\n"));
        list.add(new NoteEntity(id++, "Ремонт", "трубы, смесители, муфты, " +
                "фитинговые соединения, тройники, уголки, ниппели, шаровые краны, отводы"));
        list.add(new NoteEntity(id++, "День рождения", "Парк Победы беседка №5 в 17:00"));
        list.add(new NoteEntity(id++, "Фильмы", "1. Гнев человеческий\n" +
                "2. Лига справедливости Зака Снайдера\n" +
                "3. Майор Гром: Чумной Доктор\n" +
                "4. Дюна\n" +
                "5. Круэлла"));
        list.add(new NoteEntity(id++, "Пакет документов", "Паспорт, ИНН, Полис"));
        list.add(new NoteEntity(id++, "Зубной", "В 18:00 28.01"));
        list.add(new NoteEntity(id++, "Оплатить интернет", "680 руб."));
        return list;
    }

}
