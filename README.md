Zadaniem programu jest umożliwienie segregowanie plików. Program powinien:
Stworzyć strukturę katalogów:

HOME
DEV
TEST

W momencie pojawienia się w katalogu HOME pliku w zależności od rozszerzenia przeniesie go do folderu wg następujących reguł:

1.plik z rozszerzeniem .jar, którego godzina utworzenia jest parzysta przenosimy do folderu DEV

2. plik z rozszerzeniem .jar, którego godzina utworzenia jest nieparzysta przenosimy do folderu TEST

3. plik z rozszerzeniem .xml, przenosimy do folderu DEV

Dodatkowo w nowo stworzonym pliku /home/count.txt należy przechowywać liczbę przeniesionych plików (wszystkich i w podziale na
katalogi), plik powinien w każdym momencie działania programu przechowywać aktualną liczbę przetworzonych plików.
