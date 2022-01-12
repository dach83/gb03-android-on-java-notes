package com.example.gb03_android_on_java_notes.data;

import com.example.gb03_android_on_java_notes.domain.Note;
import com.example.gb03_android_on_java_notes.domain.NoteRepository;

import java.util.ArrayList;
import java.util.List;

public class MemoryNoteRepository implements NoteRepository {

    private static int currentId = 0;
    private final List<Note> notes = new ArrayList<>(sampleNoteList());

    @Override
    public List<Note> getNotes() {
        return notes;
    }

    @Override
    public Note createNote() {
        Note note = new Note(++currentId);
        notes.add(note);
        return note;
    }

    @Override
    public boolean updateNote(Note note) {
        return true; // ничего не делаем, т.к. все заметки хранятся в памяти и обновляются во время редактириования
    }

    @Override
    public Note findNote(int noteId) {
        for (Note note : notes) {
            if (note.getId() == noteId) {
                return note;
            }
        }
        return null;
    }

    @Override
    public boolean removeNote(int noteId) {
        Note note = findNote(noteId);
        if (note != null) {
            notes.remove(note);
            return true;
        } else {
            return false;
        }
    }

    private static List<Note> sampleNoteList() {
        List<Note> list = new ArrayList<>();
        list.add(new Note(++currentId, "Кредит", "8700 руб"));
        list.add(new Note(++currentId, "Рецепт салата с авокадо", "Нарежьте помидоры, огурцы и " +
                "авокадо крупными кусочками, а лук — тонкими полосками. Добавьте рубленую петрушку, масло, " +
                "лимонный сок, соль и перец и перемешайте."));
        list.add(new Note(++currentId, "Ссылки", "1. http://www.specialdefects.com/v2/ - походить по песку\n" +
                "2. https://multator.ru/draw/ - рисовать мультики\n" +
                "3. http://mailfuture.ru/write/ - письмо в будущее\n" +
                "4. http://kakoysegodnyadennedeli.ru/ - какой сегодня день недели\n" +
                "5. http://first-ever.ru/ - сайт про всё самое первое\n" +
                "6. http://proteys.info/404/ - джага\n" +
                "7. http://e.ggtimer.com/ - таймер для ежедневных нужд\n" +
                "8. http://tonematrix.audiotool.com/ - сочинять музыку\n" +
                "9. https://virtualpiano.net/ - играть на синтезаторе\n"));
        list.add(new Note(++currentId, "Ремонт", "трубы, смесители, муфты, " +
                "фитинговые соединения, тройники, уголки, ниппели, шаровые краны, отводы"));
        list.add(new Note(++currentId, "День рождения", "Парк Победы беседка №5 в 17:00"));
        list.add(new Note(++currentId, "Фильмы", "1. Рокки\n" +
                "2. Терминатор\n" +
                "3. Фантомас\n" +
                "4. Дюна\n" +
                "5. Три мушкетера"));
        list.add(new Note(++currentId, "Пакет документов", "Паспорт, ИНН, Полис"));
        list.add(new Note(++currentId, "Зубной", "В 18:00 28.01"));
        list.add(new Note(++currentId, "Оплатить интернет", "680 руб."));
        list.add(new Note(++currentId, "Бильярд", "Баричхолл в пятницу в 19:00"));
        list.add(new Note(++currentId, "Горные лыжи", "Мраткино"));
        return list;
    }

}
