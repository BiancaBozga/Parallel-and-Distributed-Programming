
$param1 = $args[0] # Nume fisier java
#Write-Host $param1

$param2 = $args[1] # No of threads
#Write-Host $param2

$param3 = $args[2] # No of runs
#Write-Host $param3

$param4 = $args[3] # No of file
#Write-Host $param4

$param5 = if ($args[4] -ne "sequential") { 1 } else { 0 }

$method=$args[4]

$type=$args[5]

# Type of program: 0 - sequential, 1 - parallel, 2 - both, but verify if results are equal
#Write-Host $param5
$classPath="C:\Users\computer\IdeaProjects\untitled6\out\production\untitled6"
<#
$param4 = $args[3] # ?
Write-Host $param2
#>

# Executare class Java

$suma = 0
$fileName =$param4
$regex = [regex]"input(\d+_\d+_\d+)\.txt"
$matches = $regex.Match($fileName)

if ($matches.Success) {
    $nr_part = $matches.Groups[1].Value
    Write-Output $nr_part
}
for ($i = 0; $i -lt $param3; $i++){
    Write-Host "Rulare" ($i+1)
    $a = java -cp $classPath $args[0] $args[1] $args[2] $args[3] $args[4] $args[5]   # rulare class java
    Write-Host $a[$a.length-1]
    $suma += $a[$a.length-1]

    if ($param5 -eq 1) {
    if ($args[4] -ne "block") {
        $fileA ="C:\Users\computer\IdeaProjects\untitled6\files\outputs\${method}_${type}_${nr_part}_${param2}.txt"}
    Else
    {
    $fileA ="C:\Users\computer\IdeaProjects\untitled6\files\outputs\block_${nr_part}_${param2}.txt"
    }
        $fileB = "C:\Users\computer\IdeaProjects\untitled6\files\outputs\sequential_${nr_part}.txt"

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
Add-Content outJ.csv "$($args[3]),$($args[1]),$($media),$($method) $($type)"