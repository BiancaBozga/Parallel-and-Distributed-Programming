
$param1 = $args[0] # Nume fisier exe
$param2 = $args[1] # nume fisier text 
$param3 = $args[2] # Nume metoda 
$param4 = $args[3] # nume par method 
$param5 = $args[4] # no of threads 
$param6 = $args[5] # no of runs



# Executare exe in cmd mode

$suma = 0

for ($i = 0; $i -lt $param6; $i++){
    Write-Host "Rulare" ($i+1)
    $a = (cmd /c .\$param1 $param2 $param3 $param4 $param5 $param6 2`>`&1)
    Write-Host $a[$a.length-1]
    $suma += $a[$a.length-1]
    Write-Host ""
}
$media = $suma / $i
#Write-Host $suma
Write-Host "Timp de executie mediu:" $media

# Creare fisier .csv
if (!(Test-Path outC.csv)){
    New-Item outC.csv -ItemType File
    #Scrie date in csv
    Set-Content outC.csv 'Tip Matrice,Tip alocare,Nr threads,Timp executie,Metoda'
}

$mode = ""

# Verifică valoarea lui $param1
if ($param1 -eq "main_dinamic") {
    $mode = "dinamic"
} elseif ($param1 -eq "main_static") {
    $mode = "static"
}

# Append
Add-Content outC.csv "$($args[1]),$mode,$($args[4]),$($media),$($args[2]) $($args[3]) "