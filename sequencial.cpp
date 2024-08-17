#include <iostream>
#include <vector>
#include <chrono>
#include <cmath>

void crivoEratostenes(int limiteSuperior) {
    std::vector<bool> numeros(limiteSuperior + 1, true);
    numeros[0] = numeros[1] = false;
    int limiteRaizQuadrada = sqrt(limiteSuperior);

    auto inicio = std::chrono::high_resolution_clock::now();
    for (int i = 2; i <= limiteRaizQuadrada; ++i) {
        if (numeros[i]) {
            for (int j = i * i; j <= limiteSuperior; j += i) {
                numeros[j] = false;
            }
        }
    }
    auto fim = std::chrono::high_resolution_clock::now();

    int qtd = 0;
    // for (int i = 2; i <= limiteSuperior; i++) {
    //     if (numeros[i]) qtd++;
    // }
    auto duracao = std::chrono::duration_cast<std::chrono::duration<double, std::milli>>(fim - inicio);
    std::cout << "Quantidade primos: " << qtd << std::endl;
    std::cout << "Tempo de execucao sequencial: " << duracao.count() << " ms" << std::endl;
}

int main(int argc, char *argv[]) {
    int limiteSuperior;
    std::cout << "Limite superior: ";
    std::cin >> limiteSuperior;
    crivoEratostenes(limiteSuperior);
    return 0;
}
