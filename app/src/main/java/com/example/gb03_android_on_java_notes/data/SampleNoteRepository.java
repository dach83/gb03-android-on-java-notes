package com.example.gb03_android_on_java_notes.data;

import com.example.gb03_android_on_java_notes.domain.NoteEntity;
import com.example.gb03_android_on_java_notes.domain.NoteRepository;

import java.util.ArrayList;
import java.util.List;

public class SampleNoteRepository implements NoteRepository {

    private static int currentId = 0;
    private final List<NoteEntity> notes = new ArrayList<>(sampleNoteList());

    @Override
    public List<NoteEntity> getNotes() {
        return notes;
    }

    @Override
    public NoteEntity createNote() {
        NoteEntity note = new NoteEntity(++currentId);
        notes.add(note);
        return note;
    }

    @Override
    public NoteEntity findNote(int noteId) {
        for (NoteEntity note : notes) {
            if (note.getId() == noteId) {
                return note;
            }
        }
        return null;
    }

    @Override
    public boolean removeNote(int noteId) {
        NoteEntity note = findNote(noteId);
        if (note != null) {
            notes.remove(note);
            return true;
        } else {
            return false;
        }
    }

    private static List<NoteEntity> sampleNoteList() {
        List<NoteEntity> list = new ArrayList<>();
        list.add(new NoteEntity(++currentId, "Кредит", "8700 руб"));
        list.add(new NoteEntity(++currentId, "Рецепт салата с авокадо", "Нарежьте помидоры, огурцы и " +
                "авокадо крупными кусочками, а лук — тонкими полосками. Добавьте рубленую петрушку, масло, " +
                "лимонный сок, соль и перец и перемешайте."));
        list.add(new NoteEntity(++currentId, "Ссылки", "1. http://www.specialdefects.com/v2/ - походить по песку\n" +
                "2. https://multator.ru/draw/ - рисовать мультики\n" +
                "3. http://mailfuture.ru/write/ - письмо в будущее\n" +
                "4. http://kakoysegodnyadennedeli.ru/ - какой сегодня день недели\n" +
                "5. http://first-ever.ru/ - сайт про всё самое первое\n" +
                "6. http://proteys.info/404/ - джага\n" +
                "7. http://e.ggtimer.com/ - таймер для ежедневных нужд\n" +
                "8. http://tonematrix.audiotool.com/ - сочинять музыку\n" +
                "9. https://virtualpiano.net/ - играть на синтезаторе\n"));
        list.add(new NoteEntity(++currentId, "Ремонт", "трубы, смесители, муфты, " +
                "фитинговые соединения, тройники, уголки, ниппели, шаровые краны, отводы"));
        list.add(new NoteEntity(++currentId, "День рождения", "Парк Победы беседка №5 в 17:00"));
        list.add(new NoteEntity(++currentId, "Фильмы", "1. Рокки\n" +
                "2. Терминатор\n" +
                "3. Фантомас\n" +
                "4. Дюна\n" +
                "5. Три мушкетера"));
        list.add(new NoteEntity(++currentId, "Пакет документов", "Паспорт, ИНН, Полис"));
        list.add(new NoteEntity(++currentId, "Зубной", "В 18:00 28.01"));
        list.add(new NoteEntity(++currentId, "Оплатить интернет", "680 руб."));
        list.add(new NoteEntity(++currentId, "Бильярд", "Баричхолл в пятницу в 19:00"));
        list.add(new NoteEntity(++currentId, "Горные лыжи", "Мраткино"));
        return list;
    }

}
