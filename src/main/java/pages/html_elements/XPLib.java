package pages.html_elements;

public interface XPLib {
    String
            // типичные выражения xpath
            r = "descendant::",
            f = "/following::",
            p = "/preceding-sibling::",
    // шаблоны для подстановки. использование шаблона в xpath добавляет префикс в названии, например sHeader, nLabel. Префиксы переходят в конкатенирующие имена
    NAME = "name",
    INDEX = "index",

    SYMBOLS_OLD = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯё«»:*?,. ", // заменяются буквы, удаляются символы
    SYMBOLS_NEW = "абвгдеежзийклмнопрстуфхцчшщъыьэюяе",
    $NAME = "[translate(normalize-space(.),'" + SYMBOLS_OLD + "','" + SYMBOLS_NEW + "')" +
                    " =translate('{{ " + NAME + " }}','" + SYMBOLS_OLD + "','" + SYMBOLS_NEW + "')]",
    $NAME_CONTAINS = "[contains(translate(normalize-space(.),'" + SYMBOLS_OLD + "','" + SYMBOLS_NEW + "')" +
                    ", translate('{{ " + NAME + " }}','" + SYMBOLS_OLD + "','" + SYMBOLS_NEW + "'))]",

    $INDEX = "[{{ " + INDEX + " }}]";
}
