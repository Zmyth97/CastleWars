package com.desitum.castleWars.objects;

import com.desitum.castleWars.data.CardActions;
import com.desitum.castleWars.packs.FlamePack;
import com.desitum.castleWars.packs.JapanesePack;
import com.desitum.castleWars.world.GameInterface;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 5/20/2015.
 */
public class Hand {
    private ArrayList<Card> cardList;
    private ArrayList<Integer> peopleCards;
    private GameInterface gi;

    public Hand(GameInterface gameInterface) {
        this.gi = gameInterface;
        cardList = new ArrayList<Card>();
        peopleCards = new ArrayList<Integer>();
        fillPeopleCardList();
    }

    public void addCardToHand(Card card) {
        cardList.add(card);
        gi.addCardToWorld(card);
    }

    public void removeCardFromHand(Card card) {
        cardList.remove(card);
        gi.removeCardFromWorld(card);
    }

    private void checkCardCosts() {
        for (Card card : cardList) {
            if (gi.getPlayer1().getHand().equals(this)) {
                if (card.getCardType() == Card.BUILD) {
                    if (card.getCardCost() <= gi.getResources().getPlayerStones()) {
                        if (peopleCards.contains(card.getCardID())) {
                            if (checkBuilderMax(0, card.getCardID())) {
                                card.disable();
                                card.atMax = true;
                            } else {
                                card.enable();
                                card.atMax = false;
                            }
                        } else {
                            card.enable();
                        }
                    } else {
                        card.disable();
                    }
                } else if (card.getCardType() == Card.ATTACK) {
                    if (card.getCardCost() <= gi.getResources().getPlayerWeapons()) {
                        if (peopleCards.contains(card.getCardID())) {
                            if (checkSoldierMax(0, card.getCardID())) {
                                card.disable();
                                card.atMax = true;
                            } else {
                                card.enable();
                                card.atMax = false;
                            }
                        } else {
                            card.enable();
                        }
                    } else {
                        card.disable();
                    }
                } else {
                    if (card.getCardCost() <= gi.getResources().getPlayerGems()) {
                        if (peopleCards.contains(card.getCardID())) {
                            if (checkWizardMax(0, card.getCardID())) {
                                card.disable();
                                card.atMax = true;
                            } else {
                                card.enable();
                                card.atMax = false;
                            }
                        } else {
                            card.enable();
                        }
                    } else {
                        card.disable();
                    }
                }
            } else {
                if (card.getCardType() == Card.BUILD) {
                    if (card.getCardCost() <= gi.getResources().getComputerStones()) {
                        if (peopleCards.contains(card.getCardID())) {
                            if (checkBuilderMax(1, card.getCardID())) {
                                card.disable();
                                card.atMax = true;
                            } else {
                                card.enable();
                                card.atMax = false;
                            }
                        } else {
                            card.enable();
                        }
                    } else {
                        card.disable();
                    }
                } else if (card.getCardType() == Card.ATTACK) {
                    if (card.getCardCost() <= gi.getResources().getComputerWeapons()) {
                        if (peopleCards.contains(card.getCardID())) {
                            if (checkSoldierMax(1, card.getCardID())) {
                                card.disable();
                                card.atMax = true;
                            } else {
                                card.enable();
                                card.atMax = false;
                            }
                        } else {
                            card.enable();
                        }
                    } else {
                        card.disable();
                    }
                } else {
                    if (card.getCardCost() <= gi.getResources().getComputerGems()) {
                        if (peopleCards.contains(card.getCardID())) {
                            if (checkWizardMax(1, card.getCardID())) {
                                card.disable();
                                card.atMax = true;
                            } else {
                                card.enable();
                                card.atMax = false;
                            }
                        } else {
                            card.enable();
                        }
                    } else {
                        card.disable();
                    }
                }
            }
        }
    }

    private void fillPeopleCardList() {
        peopleCards.add(CardActions.ARCHITECT);
        peopleCards.add(CardActions.RECRUITER);
        peopleCards.add(CardActions.MAGE);
        peopleCards.add(FlamePack.FORGE);
        peopleCards.add(FlamePack.FLAME_LEGION);
        peopleCards.add(FlamePack.FIRE_SHAMAN);
        peopleCards.add(JapanesePack.MONASTERY);
        peopleCards.add(JapanesePack.DOJO);
        peopleCards.add(JapanesePack.SHRINE);
        peopleCards.add(FlamePack.PHEONIX);
        peopleCards.add(JapanesePack.SEPPUKU);
    }

    private boolean checkBuilderMax(int player, int card) {
        boolean atMax = false;
        if (player == 0) {
            if (card == CardActions.ARCHITECT) {
                if (gi.getResources().getPlayerBuilders() > 5) {
                    atMax = true;
                }
            } else {
                if (gi.getResources().getPlayerBuilders() > 4) {
                    atMax = true;
                }
            }
        } else {
            if (card == CardActions.ARCHITECT) {
                if (gi.getResources().getComputerBuilders() > 5) {
                    atMax = true;
                }
            } else {
                if (gi.getResources().getComputerBuilders() > 4) {
                    atMax = true;
                }
            }
        }

        return atMax;
    }

    private boolean checkSoldierMax(int player, int card) {
        boolean atMax = false;
        if (player == 0) {
            if (card == CardActions.RECRUITER) {
                if (gi.getResources().getPlayerSoldiers() > 5) {
                    atMax = true;
                }
            } else {
                if (gi.getResources().getPlayerSoldiers() > 4) {
                    atMax = true;
                }
            }
        } else {
            if (card == CardActions.RECRUITER) {
                if (gi.getResources().getComputerSoldiers() > 5) {
                    atMax = true;
                }
            } else {
                if (gi.getResources().getComputerSoldiers() > 4) {
                    atMax = true;
                }
            }
        }

        return atMax;
    }

    private boolean checkWizardMax(int player, int card) {
        boolean atMax = false;
        if (player == 0) {
            if (card == CardActions.MAGE) {
                if (gi.getResources().getPlayerWizards() > 5) {
                    atMax = true;
                }
            } else {
                if (gi.getResources().getPlayerWizards() > 4) {
                    atMax = true;
                }
            }
        } else {
            if (card == CardActions.MAGE) {
                if (gi.getResources().getComputerWizards() > 5) {
                    atMax = true;
                }
            } else {
                if (gi.getResources().getComputerWizards() > 4) {
                    atMax = true;
                }
            }
        }

        return atMax;
    }

    public ArrayList<Card> getCardsInHand() {
        return cardList;
    }

    public void update(float delta) {
        for (Card c : cardList) {
            c.update(delta);
        }
        checkCardCosts();
    }
}
