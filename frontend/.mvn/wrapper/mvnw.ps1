# Baixa o Maven (so na primeira vez) e executa com os argumentos recebidos.
$ErrorActionPreference = 'Stop'
$ProgressPreference = 'SilentlyContinue'

$linha = Get-Content (Join-Path $PSScriptRoot 'maven-wrapper.properties') | Where-Object { $_ -match '^distributionUrl=' }
$url = ($linha -split '=', 2)[1].Trim()
$nome = [IO.Path]::GetFileNameWithoutExtension($url)
$destino = Join-Path $env:USERPROFILE ".m2\wrapper\dists\$nome"

$mvn = Get-ChildItem $destino -Filter 'mvn.cmd' -Recurse -ErrorAction SilentlyContinue | Select-Object -First 1
if (-not $mvn) {
    New-Item -ItemType Directory -Force $destino | Out-Null
    $zip = Join-Path $destino "$nome.zip"
    Write-Host "Baixando Maven de $url ..."
    [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
    Invoke-WebRequest -Uri $url -OutFile $zip -UseBasicParsing
    Expand-Archive -Path $zip -DestinationPath $destino -Force
    Remove-Item $zip
    $mvn = Get-ChildItem $destino -Filter 'mvn.cmd' -Recurse | Select-Object -First 1
}

Push-Location (Split-Path (Split-Path $PSScriptRoot))
try {
    & $mvn.FullName @args
    $codigo = $LASTEXITCODE
} finally {
    Pop-Location
}
exit $codigo
