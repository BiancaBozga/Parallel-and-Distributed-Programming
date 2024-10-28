
$param1 = $args[0] # Nume fisier java
#Write-Host $param1

$param2=$args[1] #number of runs

$param3 = $args[2] # No of file

$param4=$args[3] #method

$classPath="C:\Users\computer\IdeaProjects\Lab2_PPD\out\production\Lab2_PPD"
$method=$param4
$suma = 0
$fileName =$param3
$regex = [regex]"input(\d+_\d+_\d+)\.txt"
$matches = $regex.Match($fileName)
if ($args[3] -ne "lines") {
    $param5 = 0 # No of threads
}
else {
$param5=$args[4]
}
if ($matches.Success) {
    $nr_part = $matches.Groups[1].Value
    Write-Output $nr_part
}
for ($i = 0; $i -lt $param2; $i++){
    Write-Host "Rulare" ($i+1)
    $a = java -cp $classPath $args[0]  $args[2] $args[3] $param5    # rulare class java
     Write-Host $a
#     Write-Host $a[$a.length-1]
    $suma += $a


    $fileA ="C:\Users\computer\IdeaProjects\Lab2_PPD\files\outputs\lines_${nr_part}_${param5}.txt"
    $fileB = "C:\Users\computer\IdeaProjects\Lab2_PPD\files\outputs\sequential_${nr_part}.txt"
if ($args[3] -ne "sequential") {

        if(Compare-Object -ReferenceObject $(Get-Content $fileA) -DifferenceObject $(Get-Content $fileB))
            {"files are different"}
        Else {"files are the same"}
}

    Write-Host ""
}
$media = $suma / $i
#Write-Host $suma
Write-Host "Timp de executie mediu:" $media

# Creare fisier .csv
if (!(Test-Path outJ.csv)){
    New-Item outJ.csv -ItemType File
    #Scrie date in csv
    Set-Content outJ.csv 'Tip Matrice,Nr threads,Timp executie,Metoda'
}

# Append
Add-Content outJ.csv "$($args[2]),$($param5),$($media),$($method)"