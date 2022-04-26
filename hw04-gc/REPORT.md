Домашнее задание:

Описание/Пошаговая инструкция выполнения домашнего задания:
Есть готовое приложение (модуль homework)
Запустите его с размером хипа 256 Мб и посмотрите в логе время выполнения.
Пример вывода:
spend msec:18284, sec:18
Увеличьте размер хипа до 2Гб, замерьте время выполнения.
Результаты запусков записывайте в таблицу.
Определите оптимальный размер хипа, т.е. размер, превышение которого,
не приводит к сокращению времени выполнения приложения.
Оптимизируйте работу приложения.
Т.е. не меняя логики работы (но изменяя код), сделайте так, чтобы приложение работало быстро с минимальным хипом.
Повторите измерения времени выполнения программы для тех же значений размера хипа.

До оптимизации приложения:

Оптимальный размер хипа - 768m

-Xms256m -Xmx256m - Exception in thread "main" java.lang.OutOfMemoryError: Java heap space

-Xms384m -Xmx384m - spend msec:12148, sec:12
spend msec:12109, sec:12
spend msec:12296, sec:12
spend msec:12118, sec:12

-Xms512m -Xmx512m - spend msec:11838, sec:11
spend msec:11840, sec:11
spend msec:11732, sec:11
spend msec:12017, sec:12

-Xms640m -Xmx640m - spend msec:11189, sec:11
spend msec:11154, sec:11
spend msec:11236, sec:11
spend msec:11395, sec:11

-Xms768m -Xmx768m - spend msec:10714, sec:10
spend msec:10727, sec:10
spend msec:10548, sec:10
spend msec:11264, sec:11

-Xms896m -Xmx896m - spend msec:10593, sec:10
spend msec:10826, sec:10
spend msec:10762, sec:10
spend msec:10801, sec:10

-Xms1024m -Xmx1024m - spend msec:10254, sec:10
spend msec:10405, sec:10
spend msec:10395, sec:10
spend msec:10479, sec:10

-Xms1152m -Xmx1152m - spend msec:10367, sec:10
spend msec:10190, sec:10
spend msec:10228, sec:10
spend msec:10279, sec:10

-Xms2048m -Xmx2048m - spend msec:9971, sec:9
spend msec:9735, sec:9
spend msec:9794, sec:9
spend msec:9774, sec:9

После оптимизации приложения:

Оптимальный размер хипа - 1m

-Xms1m -Xmx1m - spend msec:792, sec:0
spend msec:769, sec:0
spend msec:778, sec:0
spend msec:812, sec:0

-Xms2m -Xmx2m - spend msec:797, sec:0
spend msec:770, sec:0
spend msec:773, sec:0
spend msec:758, sec:0

-Xms128m -Xmx128m - spend msec:734, sec:0
spend msec:739, sec:0
spend msec:766, sec:0
spend msec:767, sec:0

-Xms256m -Xmx256m - spend msec:771, sec:0
spend msec:786, sec:0
spend msec:773, sec:0
spend msec:844, sec:0